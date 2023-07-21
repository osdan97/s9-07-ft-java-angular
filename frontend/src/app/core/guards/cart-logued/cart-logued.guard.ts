import { Injectable, inject } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanMatch,
  RouterStateSnapshot,
  Route,
  UrlSegment,
  Router,
} from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { AuthService } from '../../services/auth/auth.service';

@Injectable({ providedIn: 'root' })
export class CartLoguedGuard implements CanActivate, CanMatch {
  isLoged = false;

  authService = inject(AuthService);
  cookieService = inject(CookieService);
  router = inject(Router);

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean | Observable<boolean> {
    return this.verifiedJWT();
  }
  canMatch(
    route: Route,
    segments: UrlSegment[]
  ): boolean | Observable<boolean> {
    return this.verifiedJWT();
  }

  verifiedJWT(): boolean {
    this.authService.getAutenticate().subscribe((value) => {
      console.log(value);
      if (!value) {
        this.router.navigate(['/cart-unlogged']);
        return true;
      }

      return false;
    });

    return true;
  }
}
