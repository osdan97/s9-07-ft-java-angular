import { Component, OnInit, inject, signal } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CheckoutService } from '../../../core/services/checkout/checkout.service';
import { CookieService } from 'ngx-cookie-service';
import { CartProduct } from 'src/app/core/interfaces/products.interfaces';
import { CartService } from 'src/app/core/services/cart/cart.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss'],
})
export class DetailsComponent implements OnInit {
  cartProduct = signal<CartProduct[]>([]);
  totalPriceProduct = 0;
  totalProductToCart = 0;
  totalProductWeight = 0;
  date = new Date();
  visible = false;

  paymentForm!: FormGroup;
  shippingMethod!: string;
  imagePath!: string;
  userData!: any;

  private formBuilder = inject(FormBuilder);
  private checkoutService = inject(CheckoutService);
  private cookieService = inject(CookieService);
  private cartService = inject(CartService);
  private router = inject(Router);

  ngOnInit(): void {
    this.paymentForm = this.initFormPayment();
    this.shippingMethod = (
      localStorage.getItem('shippingMethod') || ''
    ).toLowerCase();

    this.userData = JSON.parse(localStorage.getItem('userData') || '{}');

    if (this.shippingMethod === '"dhl"') {
      this.imagePath = '../../../../assets/images/dhl.svg';
    } else if (this.shippingMethod === '"ups"') {
      this.imagePath = '../../../../assets/images/ups.svg';
    } else {
      this.imagePath = '../../../../assets/images/seur.jpg';
    }

    this.getCart();
  }

  getCart() {
    const products = this.cartService.getCart();
    this.cartProduct.set(products);
    this.totalPrice();
    this.totalQuantity();
    this.totalWeight();
  }

  totalPrice() {
    let total = 0;
    this.cartProduct().forEach((product) => {
      total += product.price * product.quantity;
    });

    this.totalPriceProduct = total;
  }

  totalQuantity() {
    const totalProductos = this.cartProduct().reduce(
      (total, producto) => total + producto.quantity,
      0
    );

    this.totalProductToCart = totalProductos;
  }

  totalWeight() {
    const totalProductos = this.cartProduct().reduce(
      (total, producto) => total + producto.weight,
      0
    );

    this.totalProductWeight = totalProductos;
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
    // if (this.paymentForm.invalid) return;

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
      this.visible = true;

      this.cartService.clearCart();

      setTimeout(() => {
        this.router.navigate(['/']);
      }, 3000);
    });
  }
}
