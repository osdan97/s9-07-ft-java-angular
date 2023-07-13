import { Component, Input, OnInit, inject, signal } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Notify } from 'notiflix/build/notiflix-notify-aio';

@Component({
  selector: 'app-input-cart',
  templateUrl: './input-cart.component.html',
  styleUrls: ['./input-cart.component.scss'],
})
export class InputCartComponent implements OnInit {
  @Input() product!: any;
  @Input() showButton = true;
  @Input() changeClass = true;
  addCartForm!: FormGroup;

  quantity = signal<number>(1);

  formBuilder = inject(FormBuilder);

  ngOnInit(): void {
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
      id: this.product.id,
      quantity: this.addCartForm.value.quantity,
    };
    console.log(body);
  }

  showMessageFailed(): void {
    Notify.failure('Debes seleccionar al menos un producto');
  }
}
