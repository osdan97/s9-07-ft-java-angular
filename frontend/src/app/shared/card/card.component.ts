import { Product } from './../../core/interfaces/user.interfaces';
import { Component, Input, OnInit, inject, signal } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

import { Notify } from 'notiflix/build/notiflix-notify-aio';
import { take } from 'rxjs';
import { ProductsResponse } from 'src/app/core/interfaces/products.interfaces';
import { UserService } from 'src/app/core/services/user/user.service';

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
  isLogued = false;
  changeRouter = false;

  quantity = signal<number>(1);
  viewIsFavorite = signal<boolean>(false);

  formBuilder = inject(FormBuilder);
  userService = inject(UserService);
  cookieService = inject(CookieService);
  route = inject(ActivatedRoute);
  router = inject(Router);

  ngOnInit(): void {
    this.addCartForm = this.initForm();

    this.userService.favorites$.subscribe((favorites) => {
      const response = favorites.some(
        (item: Product) => item.id === this.product.id
      );
      this.viewIsFavorite.set(!response);
    });

    const currentUrl = this.router.url;
    const productRoute = currentUrl.split('/')[1];

    if (productRoute === 'product') {
      this.changeRouter = true;
    } else {
      this.changeRouter = false;
    }
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
  }

  showMessageFailed(): void {
    Notify.failure('Debes seleccionar al menos un producto');
  }

  isFavorite(product: ProductsResponse) {
    const verifyToken = this.cookieService.check('accessToken');

    if (!verifyToken) {
      this.isLogued = true;
      return;
    } else {
      this.isLogued = false;
    }

    const token = this.cookieService.get('accessToken');

    this.userService.refreshFavorites(token);

    if (this.viewIsFavorite()) {
      this.userService
        .addFavoriteProduct(token, product.id)
        .subscribe((resp) => {
          // sessionStorage.setItem('favorites', JSON.stringify(resp));
        });
    }
  }

  closeDialog(): void {
    this.isLogued = false;
  }
}
