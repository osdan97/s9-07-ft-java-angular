import { Component, OnInit, inject } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  FormBuilder,
} from '@angular/forms';
import {
  ShippingDetailsList,
  UserData,
} from 'src/app/core/interfaces/auth.interfaces';
import { FormCheckout } from 'src/app/core/interfaces/checkout';
import { CheckoutService } from 'src/app/core/services/checkout/checkout.service';

@Component({
  selector: 'app-check-pay',
  templateUrl: './check-pay.component.html',
  styleUrls: ['./check-pay.component.scss'],
})
export class CheckPayComponent implements OnInit {
  shippingDetail!: FormGroup;
  shippingMethod!: FormGroup;

  userData!: UserData;
  phoneNumber!: string;
  shippingData!: ShippingDetailsList;

  show = false;
  show1 = false;
  show2 = false;

  formBuilder = inject(FormBuilder);
  chekautService = inject(CheckoutService);

  ngOnInit(): void {
    this.getUserData();
    this.shippingDetail = this.initFormShippingDetail();
    this.shippingMethod = this.initFormshippingMethod();
  }

  sel_pay = new FormControl('');
  num_cre = new FormControl('');
  ven_cre = new FormControl('');
  cod_cre = new FormControl('');
  check_cre = new FormControl('');

  form2 = new FormGroup({
    sel_pay: this.sel_pay,
    num_cre: this.num_cre,
    ven_cre: this.ven_cre,
    cod_cre: this.cod_cre,
    check_cre: this.check_cre,
  });

  form_person() {
    if (this.shippingDetail.valid) {
      this.show = true;
    }
  }

  form_send() {
    if (this.shippingMethod.valid) {
      this.show1 = true;
    }
  }

  form_pay() {
    if (this.shippingMethod.valid) {
      this.show2 = true;
    }
  }

  getUserData() {
    // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
    this.userData = JSON.parse(localStorage.getItem('userData')!);

    const arrayPhone = this.userData.phonesList[0];
    const number = `+${arrayPhone.countryCode}-${arrayPhone.cityCode}-${arrayPhone.phoneNumber}`;

    const shipping = this.userData.shippingDetailsList[0];

    this.phoneNumber = number;
    this.shippingData = shipping;
  }

  initFormShippingDetail(): FormGroup {
    return this.formBuilder.group({
      country: [
        this.shippingData.country,
        [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)],
      ],
      province: [
        this.shippingData.provincia,
        [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)],
      ],
      postalCode: [this.shippingData.postalCode, Validators.required],
      city: [
        this.shippingData.city,
        [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)],
      ],
      address: [this.shippingData.address1, Validators.required],
      addressDetail: [this.shippingData.address2, Validators.required],
      checkFact: [''],
    });
  }

  initFormshippingMethod(): FormGroup {
    return this.formBuilder.group({
      shippingMethod: ['', Validators.required],
      commentShipping: [''],
    });
  }

  sendData() {
    const cart = JSON.parse(localStorage.getItem('cart') || '{}');

    const orderDetailsList = cart.map((item: any) => {
      return {
        product: {
          id: item.id,
        },
        quantity: item.quantity,
      };
    });

    console.log(cart);

    const body: any = {
      account: {
        accountUuid: this.userData.accountUuid,
      },
      shippingCost: 7.95,
      orderDetailsList: orderDetailsList,
      shippingDetails: {
        shippingdetail_uuid: this.shippingData.shippingDetailUuid,
        name: this.userData.name,
        lastName: this.userData.lastName,
        address1: this.shippingData.address1,
        address2: this.shippingData.address2,
        postalCode: this.shippingData.postalCode,
        provincia: this.shippingData.provincia,
        city: this.shippingData.city,
        country: this.shippingData.country,
        gift: false,
      },
    };

    const shippingMethod = this.shippingMethod.value.shippingMethod;

    localStorage.setItem('shippingMethod', JSON.stringify(shippingMethod));

    this.chekautService.addOrder(body, this.userData.token).subscribe((res) => {
      localStorage.setItem('order', JSON.stringify(res));
    });
  }
}
