import { Component, OnInit, inject } from '@angular/core';
import { AuthService } from '../core/services/auth/auth.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-ecommerce',
  templateUrl: './ecommerce.component.html',
  styleUrls: ['./ecommerce.component.scss'],
})
export class EcommerceComponent implements OnInit {
  authService = inject(AuthService);
  cookieService = inject(CookieService);

  ngOnInit(): void {
    if (this.cookieService.check('accessToken')) {
      this.authService.setAutenticate(true);
    } else {
      this.authService.setAutenticate(false);
      localStorage.clear();
    }
  }
}
