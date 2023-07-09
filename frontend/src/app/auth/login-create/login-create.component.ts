import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-login-create',
  templateUrl: './login-create.component.html',
  styleUrls: ['./login-create.component.scss'],
})
export class LoginCreateComponent {
  name = new FormControl('', [Validators.required]);

  form = new FormGroup({
    name: this.name,
  });

  login_create() {
    console.log(this.form.value);
  }
}
