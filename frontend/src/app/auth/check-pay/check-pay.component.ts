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
  ingredient!: string;

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
  detalle_dir = new FormControl('', Validators.required);
  check_fact = new FormControl('', Validators.requiredTrue);
  envio = new FormControl('');
  coment_envio = new FormControl('');

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
  })

  login_create() {
    if (this.form.valid) {
      console.log(this.form.value);
      this.show = true;
    }
  }
}
