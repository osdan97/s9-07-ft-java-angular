import {
  Component,
  ElementRef,
  HostListener,
  OnInit,
  Renderer2,
  inject,
  signal,
} from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { UserData } from 'src/app/core/interfaces/auth.interfaces';
import { AuthService } from 'src/app/core/services/auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  headerFixed = signal<boolean>(false);
  showCategories = signal<boolean>(false);
  value: string | undefined;

  logout = false;
  visible = false;
  visibleCart = signal<boolean>(true);
  visibleMyAccount = signal<boolean>(false);

  userData!: UserData | null;
  isLoged = false;

  router = inject(Router);
  renderer = inject(Renderer2);
  elementRef = inject(ElementRef);
  authService = inject(AuthService);
  cookieService = inject(CookieService);

  ngOnInit(): void {
    this.router.events.subscribe(() => {
      this.getCurrentRoute();
      window.scrollTo(0, 0);
      // if (event instanceof NavigationEnd) {
      // }
    });

    this.clickOutMenu();

    this.isLogued();
  }

  //Función para verificar si no se encuentra en la ruta principal para mostrar el header con el menú de categorías
  getCurrentRoute() {
    if (this.router.url === '/') {
      this.showCategories.set(false);
    } else {
      this.showCategories.set(true);
    }
  }

  //Función para mostrar el modal de login
  showDialog() {
    this.visible = !this.visible;
    if (this.visibleCart() === true) {
      this.visibleCart.set(false);
      this.visibleMyAccount.set(false);
    }
  }

  //Función para mostrar el modal del carrito
  showDialogCart() {
    this.visibleCart.update((value) => !value);
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
      this.visibleCart.set(false);
    }
  }

  showDialogLogout() {
    this.logout = !this.logout;
  }

  //Función para recibir el valor del modal del carrito
  emitirValor(valor: boolean) {
    this.visibleCart.set(valor);
  }

  //funcion para saber la posición del scroll y mostrar el header fijo
  @HostListener('window:scroll', ['$event']) activeFixedHeader() {
    if (window.scrollY >= 146) {
      this.headerFixed.set(true);
    } else {
      this.headerFixed.set(false);
    }
  }

  //Función para cerrar el modal del login y carrito al dar click fuera de él
  clickOutMenu() {
    this.renderer.listen('document', 'click', (event: MouseEvent) => {
      const clickedInsideMenu = this.elementRef.nativeElement.contains(
        event.target
      );
      if (!clickedInsideMenu) {
        this.visibleCart.set(false);
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
    this.cookieService.delete('accessToken');
    localStorage.clear();
    this.isLoged = false;
    this.userData = null;
    this.showDialogLogout();
  }
}
