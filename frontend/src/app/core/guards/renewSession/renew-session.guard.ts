import { Injectable, inject } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
} from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root',
})
export class RenewSession implements CanActivate {
  authService = inject(AuthService);
  router = inject(Router);
  cookieService = inject(CookieService);

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    // Coloca aquí el código para llamar a tu servicio

    if (!this.cookieService.check('accessToken')) return true;

    const token = this.cookieService.get('accessToken');

    this.authService.renewSession(token).subscribe((res) => {
      localStorage.setItem('userData', JSON.stringify(res));
      this.cookieService.set('accessToken', res.token);
    });

    // Devuelve true si quieres permitir la navegación o false para bloquearla
    return true;
  }
}
