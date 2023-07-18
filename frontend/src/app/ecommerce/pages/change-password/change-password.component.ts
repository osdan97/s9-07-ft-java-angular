import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators, AbstractControl, ValidationErrors } from '@angular/forms';

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
    validators: [this.camposIguales('new_pass', 'confir_pass')],
  })

  camposIguales(campo1: string, campo2: string) {
    return (formGroup: AbstractControl): ValidationErrors | null => {
      const pass1 = formGroup.get(campo1)?.value;
      const pass2 = formGroup.get(campo2)?.value;

      if (pass1 !== pass2) {
        formGroup.get(campo2)?.setErrors({ noIguales: true });
        return { noIguales: true };
      }

      formGroup.get(campo2)?.setErrors(null);
      return null;
    };
  }

  password_send() {
    if (this.form.valid) {
      console.log(this.form.value);
    }
    this.form.markAllAsTouched()
  }
}
