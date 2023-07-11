import {
  Component,
  ElementRef,
  HostListener,
  OnInit,
  Renderer2,
  inject,
  signal,
} from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  headerFixed = signal<boolean>(false);
  showCategories = signal<boolean>(false);
  value: string | undefined;
  visible = false;
  visibleCart = signal<boolean>(false);

  router = inject(Router);
  renderer = inject(Renderer2);
  elementRef = inject(ElementRef);

  ngOnInit(): void {
    this.router.events.subscribe((event) => {
      this.getCurrentRoute();
      // if (event instanceof NavigationEnd) {
      // }
    });

    this.clickOutMenu();
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
    }
  }

  //Función para mostrar el modal del carrito
  showDialogCart() {
    this.visibleCart.update((value) => !value);
    if (this.visible === true) {
      this.visible = false;
    }
  }

  //Función para recibir el valor del modal del carrito
  emitirValor(valor: boolean) {
    this.visibleCart.set(valor);
  }

  //funcion para saber la posición del scroll y mostrar el header fijo
  @HostListener('window:scroll', ['$event']) activeFixedHeader() {
    if (window.scrollY >= 119) {
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
      }
    });
  }
}
