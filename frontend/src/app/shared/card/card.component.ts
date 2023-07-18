import { Component, Input, OnInit, inject, signal } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Notify } from 'notiflix/build/notiflix-notify-aio';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss'],
})
export class CardComponent implements OnInit {
  @Input() product!: any;
  @Input() showInput = true;
  @Input() changeWidth = '';

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
