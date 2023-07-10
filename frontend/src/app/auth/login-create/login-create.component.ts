import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-login-create',
  templateUrl: './login-create.component.html',
  styleUrls: ['./login-create.component.scss'],
})
export class LoginCreateComponent {
  name = new FormControl('', [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)]);
  lastname = new FormControl('', [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)]);
  email = new FormControl('', [Validators.required, Validators.pattern(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/)]);
  tel = new FormControl('', Validators.required);
  pais = new FormControl('', [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)]);
  provincia = new FormControl('', [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)]);
  postal = new FormControl('', Validators.required);
  ciudad = new FormControl('', [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)]);
  direccion = new FormControl('', Validators.required);
  detalle_dir = new FormControl('', Validators.required);
  check_fact = new FormControl('', Validators.requiredTrue)

  form = new FormGroup({
    name: this.name,
    lastname: this.lastname,
    email: this.email,
    tel: this.tel,
    pais: this.pais,
    provincia: this.provincia,
    postal: this.postal,
    ciudad: this.ciudad,
    direccion: this.direccion,
    detalle_dir: this.detalle_dir,
    check_fact: this.check_fact,
  });

  login_create() {
    console.log(this.form.value)
  }
}
