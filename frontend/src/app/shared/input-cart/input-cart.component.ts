import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  inject,
  signal,
} from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Notify } from 'notiflix/build/notiflix-notify-aio';
import {
  CartProduct,
  ProductsResponse,
} from 'src/app/core/interfaces/products.interfaces';
import { CartService } from 'src/app/core/services/cart/cart.service';

@Component({
  selector: 'app-input-cart',
  templateUrl: './input-cart.component.html',
  styleUrls: ['./input-cart.component.scss'],
})
export class InputCartComponent implements OnInit {
  @Output() addCart = new EventEmitter<any>();
  @Input() product!: ProductsResponse | CartProduct;
  @Input() showButton = true;
  @Input() changeClass = true;
  @Input() changeWidth = '';
  @Input() showInput = true;
  @Input() width = '37px';
  @Input() widthCart = false;

  addCartForm!: FormGroup;

  quantity = signal<number>(1);

  formBuilder = inject(FormBuilder);
  cartService = inject(CartService);

  ngOnInit(): void {
    if (this.product) {
      this.quantity.set(this.product?.quantity ?? 1);
    }
    this.addCartForm = this.initForm();
  }

  initForm(): FormGroup {
    return this.formBuilder.group({
      quantity: [this.quantity(), [Validators.min(1)]],
    });
  }

  changeValue(value: number): void {
    if (this.quantity() <= 1 && value === -1) {
      this.showMessageFailed();
      return;
    }

    this.quantity.update((actualValue) => actualValue + value);
    this.addCartForm.controls['quantity'].setValue(this.quantity());
  }

  changeValueCart(value: number): void {
    if (this.quantity() <= 1 && value === -1) {
      this.showMessageFailed();
      return;
    }

    this.quantity.update((actualValue) => actualValue + value);
    this.addCartForm.controls['quantity'].setValue(this.quantity());

    if (this.addCartForm.invalid) {
      this.showMessageFailed();
      return;
    }
    const body: CartProduct = {
      id: this.product?.id,
      name: this.product?.name,
      image: this.product?.image,
      price: this.product?.price,
      stock: this.product?.stock,
      weight: this.product?.weight,
      quantity: this.addCartForm.value.quantity,
    };

    this.cartService.addToCartAssigning(body);
  }

  addToCart(): void {
    if (this.addCartForm.invalid) {
      this.showMessageFailed();
      return;
    }
    const body: CartProduct = {
      id: this.product?.id,
      name: this.product?.name,
      image: this.product?.image,
      price: this.product?.price,
      stock: this.product?.stock,
      weight: this.product?.weight,
      quantity: this.addCartForm.value.quantity,
    };

    this.cartService.addToCart(body);

    this.sendProduct(body);
  }

  sendProduct(product: CartProduct): void {
    this.addCart.emit(product);
  }

  showMessageFailed(): void {
    Notify.failure('Debes seleccionar al menos un producto');
  }
}
