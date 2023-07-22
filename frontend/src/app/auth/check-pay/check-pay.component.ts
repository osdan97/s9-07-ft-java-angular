import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-check-pay',
  templateUrl: './check-pay.component.html',
  styleUrls: ['./check-pay.component.scss'],
})
export class CheckPayComponent {
  show = false;
  show1 = false;
  show2 = false;

  pais = new FormControl('', [
    Validators.required,
    Validators.pattern(/^[a-zA-Z ]+$/),
  ]);
  provincia = new FormControl('', [
    Validators.required,
    Validators.pattern(/^[a-zA-Z ]+$/),
  ]);
  postal = new FormControl('', Validators.required);
  ciudad = new FormControl('', [
    Validators.required,
    Validators.pattern(/^[a-zA-Z ]+$/),
  ]);
  direccion = new FormControl('', Validators.required);
  detalle_dir = new FormControl('');
  check_fact = new FormControl('', Validators.requiredTrue);
  envio = new FormControl('', Validators.required);
  coment_envio = new FormControl('');
  sel_pay = new FormControl('');
  num_cre = new FormControl('');
  ven_cre = new FormControl('');
  cod_cre = new FormControl('');
  check_cre = new FormControl('');

  form = new FormGroup({
    pais: this.pais,
    provincia: this.provincia,
    postal: this.postal,
    ciudad: this.ciudad,
    direccion: this.direccion,
    detalle_dir: this.detalle_dir,
    check_fact: this.check_fact,
  });

  form1 = new FormGroup({
    envio: this.envio,
    coment_envio: this.coment_envio,
  });

  form2 = new FormGroup({
    sel_pay: this.sel_pay,
    num_cre: this.num_cre,
    ven_cre: this.ven_cre,
    cod_cre: this.cod_cre,
    check_cre: this.check_cre,
  });

  form_person() {
    if (this.form.valid) {
      console.log(this.form.value);
      this.show = true;
    }
  }

  form_send() {
    if (this.form1.valid) {
      console.log(this.form1.value.envio);
      this.show1 = true;
    }
  }

  form_pay() {
    if (this.form2.valid) {
      console.log(this.form2.value);
      this.show2 = true;
    }
  }

  set_pay() {
    if (this.form2.value.sel_pay === 'paypal') {
      this.num_cre.reset();
      this.num_cre.disable();
      this.num_cre.clearValidators();
      this.ven_cre.reset();
      this.ven_cre.disable();
      this.ven_cre.clearValidators();
      this.cod_cre.reset();
      this.cod_cre.disable();
      this.cod_cre.clearValidators();
      this.check_cre.reset();
      this.check_cre.disable();
      this.check_cre.clearValidators();
    } else {
      this.num_cre.enable();
      this.num_cre.setValidators(Validators.required);
      this.ven_cre.enable();
      this.ven_cre.setValidators(Validators.required);
      this.cod_cre.enable();
      this.cod_cre.setValidators(Validators.required);
      this.check_cre.enable();
      this.check_cre.setValidators(Validators.requiredTrue);
    }
    this.form2.updateValueAndValidity();
    this.form2.markAllAsTouched();
    this.num_cre.updateValueAndValidity();
    this.ven_cre.updateValueAndValidity();
    this.cod_cre.updateValueAndValidity();
    this.check_cre.updateValueAndValidity();
  }
}
