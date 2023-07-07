import { Component, HostListener, OnInit, inject, signal } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  value: string | undefined;
  headerFixed = signal<boolean>(false);
  showCategories = signal<boolean>(false);
  visible = false;
  visibleCart = signal<boolean>(false);

  router = inject(Router);

  ngOnInit(): void {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.getCurrentRoute();
      }
    });
  }

  getCurrentRoute() {
    if (this.router.url === '/') {
      this.showCategories.set(false);
    } else {
      this.showCategories.set(true);
    }
  }

  showDialog() {
    this.visible = true;
    console.log('asd');
  }

  showDialogCart() {
    this.visibleCart.update((value) => !value);
  }

  emitirValor(valor: boolean) {
    this.visibleCart.set(valor);
  }

  @HostListener('window:scroll', ['$event']) activeFixedHeader() {
    if (window.scrollY >= 119) {
      this.headerFixed.set(true);
    } else {
      this.headerFixed.set(false);
    }
  }
}
