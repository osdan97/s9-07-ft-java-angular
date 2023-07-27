import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CheckoutService } from '../../../core/services/checkout/checkout.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss'],
})
export class DetailsComponent implements OnInit {
  paymentForm!: FormGroup;

  formBuilder = inject(FormBuilder);
  checkoutService = inject(CheckoutService);
  cookieService = inject(CookieService);

  ngOnInit(): void {
    this.paymentForm = this.initFormPayment();
  }

  initFormPayment(): FormGroup {
    return this.formBuilder.group({
      cardNumber: ['5424000000000015', [Validators.required]],
      expirationDate: ['0823', [Validators.required]],
      cardCode: ['900', [Validators.required]],
      check: ['', [Validators.required]],
    });
  }

  sendPay() {
    const transaction = JSON.parse(localStorage.getItem('order') || '{}');
    const token = this.cookieService.get('accessToken');

    const body = {
      cardNumber: this.paymentForm.value.cardNumber,
      expirationDate: this.paymentForm.value.expirationDate,
      cardCode: this.paymentForm.value.cardCode,
      currencyCode: 'EUR',
      orders: {
        transactionUuid: transaction.transactionUuid,
      },
      transaction_type: 'PAID',
    };

    this.checkoutService.addPayment(body, token).subscribe((res) => {
      console.log(res);
    });
  }
}
