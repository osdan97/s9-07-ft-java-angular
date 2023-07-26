import { Component, OnInit, inject } from '@angular/core';
import { AuthService } from '../core/services/auth/auth.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { animate, style, transition, trigger } from '@angular/animations';
import { DataService } from '../core/services/data/data.service';
import { UserService } from '../core/services/user/user.service';

@Component({
  selector: 'app-ecommerce',
  templateUrl: './ecommerce.component.html',
  styleUrls: ['./ecommerce.component.scss'],
  animations: [
    trigger('routeAnimations', [
      transition('* <=> *', [
        style({ opacity: 0 }),
        animate('500ms ease-in-out', style({ opacity: 1 })),
      ]),
    ]),
  ],
})
export class EcommerceComponent implements OnInit {
  productService = inject(DataService);
  authService = inject(AuthService);
  userService = inject(UserService);
  cookieService = inject(CookieService);
  router = inject(Router);

  ngOnInit(): void {
    if (this.cookieService.check('accessToken')) {
      this.authService.setAutenticate(true);
      this.getFavorites();
    } else {
      this.authService.setAutenticate(false);
      localStorage.removeItem('userData');
      sessionStorage.removeItem('favorites');
    }

    const products = JSON.parse(sessionStorage.getItem('products') || 'null');

    if (!products) {
      this.getProducts();
    }
  }

  getAnimationState(): string {
    // Lógica para determinar el estado de la animación según la URL actual o cualquier otra condición
    return this.router.url;
  }

  private getProducts() {
    this.productService.getProducts(1).subscribe((res) => {
      // Los productos ya han sido guardados en el sessionStorage en el servicio
    });
  }

  getFavorites() {
    const token = this.cookieService.get('accessToken');
    this.userService.refreshFavorites(token); // Actualizar los favoritos al cargar el componente

    // Suscribirse al observable para recibir actualizaciones en tiempo real
    this.userService.favorites$.subscribe((favorites) => {
      // Hacer lo que necesites con los favoritos actualizados
    });
  }
}
