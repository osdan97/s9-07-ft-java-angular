import {
  Component,
  ElementRef,
  HostListener,
  Renderer2,
  inject,
  signal,
} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { MenuItem } from 'primeng/api';
import { UserData } from 'src/app/core/interfaces/auth.interfaces';
import { CartProduct } from 'src/app/core/interfaces/products.interfaces';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { CartService } from 'src/app/core/services/cart/cart.service';
import { ProductsService } from 'src/app/core/services/products/products.service';
import { UserService } from 'src/app/core/services/user/user.service';

@Component({
  selector: 'app-header-mobile',
  templateUrl: './header-mobile.component.html',
  styleUrls: ['./header-mobile.component.scss'],
})
export class HeaderMobileComponent {
  headerFixed = signal<boolean>(false);
  items!: MenuItem[];
  isShowSidebar = false;
  panelOpenState = false;
  isShowAcordion = false;

  showCategories = signal<boolean>(false);
  searchForm!: FormGroup;
  totalProductToCart = signal<number>(0);
  cartProduct = signal<CartProduct[]>([]);

  logout = false;
  visible = false;
  visibleCart = false;
  visibleMyAccount = signal<boolean>(false);

  userData!: UserData | null;
  isLoged = false;

  router = inject(Router);
  renderer = inject(Renderer2);
  elementRef = inject(ElementRef);
  formBuilder = inject(FormBuilder);
  userService = inject(UserService);
  cartService = inject(CartService);
  productService = inject(ProductsService);
  authService = inject(AuthService);
  cookieService = inject(CookieService);

  showSidebar() {
    this.isShowSidebar = !this.isShowSidebar;
  }

  ngOnInit(): void {
    this.router.events.subscribe(() => {
      this.getCurrentRoute();
      window.scrollTo(0, 0);
      this.visibleCart = false;
      this.visible = false;
      this.visibleMyAccount.set(false);
      this.searchForm.reset();
      // this.value = undefined;
      // if (event instanceof NavigationEnd) {
      // }
    });

    this.clickOutMenu();

    this.isLogued();

    this.getProductsToCart();

    this.searchForm = this.initSearchForm();
  }

  showAcordion() {
    this.isShowAcordion = !this.isShowAcordion;
    console.log(this.isShowAcordion);
  }

  @HostListener('window:scroll', ['$event']) activeFixedHeader() {
    if (window.scrollY >= 146) {
      this.headerFixed.set(true);
    } else {
      this.headerFixed.set(false);
    }
  }

  //Función para verificar si no se encuentra en la ruta principal para mostrar el header con el menú de categorías
  getCurrentRoute() {
    if (this.router.url === '/') {
      this.showCategories.set(false);
    } else {
      this.showCategories.set(true);
    }

    if (this.router.url === '/products') {
      this.showCategories.set(false);
    }
  }

  //Función para mostrar el modal de login
  showDialog() {
    this.visible = !this.visible;
    if (this.visibleCart === true) {
      this.visibleCart = false;
      this.visibleMyAccount.set(false);
    }
  }

  //Función para mostrar el modal del carrito
  showDialogCart() {
    this.visibleCart = !this.visibleCart;
    if (this.visible === true || this.visibleMyAccount() === true) {
      this.visible = false;
      this.visibleMyAccount.set(false);
    }
  }

  //Función para mostrar el modal del menú de usuario
  showUserMenu() {
    this.visibleMyAccount.update((value) => !value);
    if (this.visibleMyAccount() === true) {
      this.visible = false;
      this.visibleCart = false;
    }
  }

  showDialogLogout() {
    this.logout = !this.logout;
  }

  //Función para recibir el valor del modal del carrito
  emitirValor(valor: boolean) {
    this.visibleCart = valor;
  }

  //Función para cerrar el modal del login y carrito al dar click fuera de él
  clickOutMenu() {
    this.renderer.listen('document', 'click', (event: MouseEvent) => {
      const clickedInsideMenu = this.elementRef.nativeElement.contains(
        event.target
      );
      if (!clickedInsideMenu) {
        this.visibleCart = false;
        this.visible = false;
        this.visibleMyAccount.set(false);
      }
    });
  }

  isLogued() {
    if (!localStorage.getItem('userData')) {
      this.authService.setAutenticate(false);
      this.cookieService.delete('accessToken');
    }

    this.authService.getAutenticate().subscribe((resp) => {
      // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
      this.userData = JSON.parse(localStorage.getItem('userData')!);
      this.isLoged = resp;
    });
  }

  closeDialog(valor: boolean) {
    this.visible = valor;
  }

  logOut() {
    const token = this.cookieService.get('accessToken');

    this.userService.refreshFavorites(token);
    this.cookieService.delete('accessToken');
    localStorage.removeItem('userData');
    this.isLoged = false;
    this.userData = null;
    this.showDialogLogout();
    this.authService.setAutenticate(false);
    sessionStorage.removeItem('favorites');
    this.router.navigate(['/']);
  }

  getProductsToCart() {
    this.cartService.getCartObservable().subscribe((products) => {
      const totalProductos = products.reduce(
        (total, producto) => total + producto.quantity,
        0
      );

      this.totalProductToCart.set(totalProductos);
    });
  }

  initSearchForm(): FormGroup {
    return this.formBuilder.group({
      product: [''],
    });
  }

  SearchFormProducts() {
    this.router.navigate(['/search'], {
      queryParams: { q: this.searchForm.value.product },
    });
    this.searchForm.reset();
  }
}
