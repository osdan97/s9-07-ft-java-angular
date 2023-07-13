import { Component, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { take } from 'rxjs';
import { AuthService } from 'src/app/core/services/auth/auth.service';

@Component({
  selector: 'app-verification-email',
  templateUrl: './verification-email.component.html',
  styleUrls: ['./verification-email.component.scss'],
})
export class VerificationEmailComponent implements OnInit {
  title = signal<string>('Tu cuenta ya ha sido verificada');
  subtitle = signal<string>('Para continuar, inicia sesión');

  route = inject(ActivatedRoute);
  authService = inject(AuthService);

  ngOnInit(): void {
    this.getToken();
  }

  getToken() {
    this.route.params.pipe(take(1)).subscribe((params) => {
      const token = params['token'];
      console.log(token);
      // this.verificationAccount(token);
    });
  }

  verificationAccount(token: string) {
    this.authService
      .verificationAccount(token)
      .pipe(take(1))
      .subscribe({
        next: (res) => {
          this.title.set('Tu cuenta ya ha sido verificada');
          this.subtitle.set('Para continuar, inicia sesión');
        },
        error: (err) => {
          console.log(err);
          this.title.set('El código de verificación no es válido');
          // this.subtitle.set('Para continuar, inicia sesión');
        },
      });
  }
}
