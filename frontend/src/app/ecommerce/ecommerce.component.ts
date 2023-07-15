import { Component, OnInit, inject } from '@angular/core';
import { AuthService } from '../core/services/auth/auth.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { animate, style, transition, trigger } from '@angular/animations';

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
  }

  getAnimationState(): string {
    // Lógica para determinar el estado de la animación según la URL actual o cualquier otra condición
    return this.router.url;
  }
}
