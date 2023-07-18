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

@Component({
  selector: 'app-input-cart',
  templateUrl: './input-cart.component.html',
  styleUrls: ['./input-cart.component.scss'],
})
export class InputCartComponent implements OnInit {
  @Output() addCart = new EventEmitter<any>();
  @Input() product!: any;
  @Input() showButton = true;
  @Input() changeClass = true;
  @Input() changeWidth = '';
  @Input() showInput = true;

  addCartForm!: FormGroup;

  quantity = signal<number>(1);

  formBuilder = inject(FormBuilder);

  ngOnInit(): void {
    this.quantity.set(this.product?.quantity);
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

  addToCart(): void {
    if (this.addCartForm.invalid) {
      this.showMessageFailed();
      return;
    }
    const body = {
      id: this.product?.id,
      name: this.product?.name,
      quantity: this.addCartForm.value.quantity,
    };
    console.log(body);

    this.sendProduct(body);
  }

  sendProduct(product: any): void {
    this.addCart.emit(product);
  }

  showMessageFailed(): void {
    Notify.failure('Debes seleccionar al menos un producto');
  }
}
