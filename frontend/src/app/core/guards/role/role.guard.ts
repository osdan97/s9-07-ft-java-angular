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
import jwtDecode from 'jwt-decode';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { Payload } from '../../interfaces/auth.interfaces';

@Injectable({ providedIn: 'root' })
export class RoleGuard implements CanActivate, CanMatch {
  cookieService = inject(CookieService);
  router = inject(Router);

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean | Observable<boolean> {
    return this.verifiedRole();
  }
  canMatch(
    route: Route,
    segments: UrlSegment[]
  ): boolean | Observable<boolean> {
    return this.verifiedRole();
  }

  verifiedRole(): boolean {
    if (!this.cookieService.check('accessToken')) return false;

    const accessToken = this.cookieService.get('accessToken');

    const { roles }: Payload = jwtDecode(accessToken);

    if (roles !== 'ROLE_ADMIN') {
      this.router.navigate(['/']);
      return false;
    }

    return true;
  }
}
