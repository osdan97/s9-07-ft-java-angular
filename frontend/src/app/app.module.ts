import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { HttpClientModule } from '@angular/common/http';

import localeEsES from '@angular/common/locales/es-419';
import { registerLocaleData } from '@angular/common';
registerLocaleData(localeEsES, {
  currency: '€',
});

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
  ],
  providers: [
    CookieService,
    {
      provide: LOCALE_ID,
      useValue: 'es-419',
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
