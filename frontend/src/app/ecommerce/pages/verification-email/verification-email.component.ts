import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Component, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, catchError, take, throwError } from 'rxjs';
import { AuthService } from 'src/app/core/services/auth/auth.service';

@Component({
  selector: 'app-verification-email',
  templateUrl: './verification-email.component.html',
  styleUrls: ['./verification-email.component.scss'],
})
export class VerificationEmailComponent implements OnInit {
  title = signal<string>('');
  subtitle = signal<string>('');

  route = inject(ActivatedRoute);
  authService = inject(AuthService);

  ngOnInit(): void {
    this.getToken();
  }

  getToken() {
    this.route.params.pipe(take(1)).subscribe((params) => {
      const token = params['token'];
      this.verificationAccount(token);
    });
  }

  verificationAccount(token: string) {
    this.authService
      .verificationAccount(token)
      .pipe(
        catchError((err: HttpErrorResponse) => {
          return this.handleErrors(err);
        })
      )
      .subscribe((res) => {
        this.title.set('¡Cuenta verificada!');
        this.subtitle.set('Ya puedes iniciar sesión');
      });
  }

  handleErrors(error: HttpErrorResponse): Observable<never> {
    if (error.status == HttpStatusCode.Forbidden) {
      this.title.set('Token inválido');
      return throwError('No tiene permisos para realizar la solicitud.');
    }
    if (error.status == HttpStatusCode.NotFound) {
      this.title.set('Token inválido');
      return throwError('El producto no existe.');
    }
    if (error.status == HttpStatusCode.InternalServerError) {
      this.title.set('Token inválido');
      return throwError('Error en el servidor.');
    }
    return throwError('Un error inesperado ha ocurrido.');
  }
}
