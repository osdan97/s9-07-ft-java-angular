import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { matchpassword } from './matchpassword.validator';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent {
  contra_actual = new FormControl('', [Validators.required, Validators.minLength(8)]);
  new_pass = new FormControl('', [Validators.required, Validators.minLength(8)]);
  confir_pass = new FormControl('', [Validators.required, Validators.minLength(8)]);

  form = new FormGroup({
    contra_actual: this.contra_actual,
    new_pass: this.new_pass,
    confir_pass: this.confir_pass
  }, {
    validators:matchpassword
  })

  password_send() {
    if (this.form.valid) {
      console.log(this.form.value);
    }
    this.form.markAllAsTouched()
  }
}
