import { Component, OnInit, inject } from '@angular/core';
import { AuthService } from '../core/services/auth/auth.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { animate, style, transition, trigger } from '@angular/animations';
import { DataService } from '../core/services/data/data.service';

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
  cookieService = inject(CookieService);
  router = inject(Router);

  ngOnInit(): void {
    if (this.cookieService.check('accessToken')) {
      this.authService.setAutenticate(true);
    } else {
      this.authService.setAutenticate(false);
      localStorage.clear();
    }

    if (!sessionStorage.getItem('products')) {
      this.getProducts();
    }
  }

  getAnimationState(): string {
    // Lógica para determinar el estado de la animación según la URL actual o cualquier otra condición
    return this.router.url;
  }

  getProducts() {
    this.productService.getProducts(1).subscribe((res) => {
      console.log(res);
      sessionStorage.setItem('products', JSON.stringify(res));
    });
  }
}
