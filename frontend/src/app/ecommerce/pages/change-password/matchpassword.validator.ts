import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export const matchpassword: ValidatorFn = (
  control: AbstractControl
): ValidationErrors | null => {
  let password = control.get('new_pass');
  let confirmpassword = control.get('confir_pass');
  if (
    password &&
    confirmpassword &&
    password?.value != confirmpassword?.value
  ) {
    return {
      passwordmatcherror: true,
    };
  }
  return null;
};
