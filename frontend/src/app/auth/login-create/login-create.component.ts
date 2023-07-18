import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import {
  FormGroup,
  Validators,
  FormBuilder,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';
import jwtDecode from 'jwt-decode';
import { take } from 'rxjs';
import {
  FormRegisterInput,
  Payload,
} from 'src/app/core/interfaces/auth.interfaces';
import {
  FormShippingDetail,
  ShippingDetailResponse,
} from 'src/app/core/interfaces/user.interfaces';
import { AuthService } from 'src/app/core/services/auth/auth.service';
import { UserService } from 'src/app/core/services/user/user.service';

@Component({
  selector: 'app-login-create',
  templateUrl: './login-create.component.html',
  styleUrls: ['./login-create.component.scss'],
})
export class LoginCreateComponent implements OnInit, OnDestroy {
  visible = false;

  registerForm!: FormGroup;

  formBuilder = inject(FormBuilder);
  authService = inject(AuthService);
  userService = inject(UserService);

  // name = new FormControl('', [ Validators.pattern(/^[a-zA-Z ]+$/)]);
  // lastname = new FormControl('', [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)]);
  // email = new FormControl('', [Validators.required, Validators.pattern(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/)]);
  // tel = new FormControl('', Validators.required);
  // pais = new FormControl('', [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)]);
  // provincia = new FormControl('', [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)]);
  // postal = new FormControl('', Validators.required);
  // ciudad = new FormControl('', [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)]);
  // direccion = new FormControl('', Validators.required);
  // detalle_dir = new FormControl('', Validators.required);
  // check_fact = new FormControl('', Validators.requiredTrue)

  // form = new FormGroup({
  //   name: this.name,
  //   lastname: this.lastname,
  //   email: this.email,
  //   tel: this.tel,
  //   pais: this.pais,
  //   provincia: this.provincia,
  //   postal: this.postal,
  //   ciudad: this.ciudad,
  //   direccion: this.direccion,
  //   detalle_dir: this.detalle_dir,
  //   check_fact: this.check_fact,
  // });

  ngOnInit(): void {
    this.registerForm = this.initRegisterForm();
  }

  ngOnDestroy(): void {
    this.visible = false;
  }

  initRegisterForm(): FormGroup {
    return this.formBuilder.group(
      {
        name: ['', [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)]],
        lastname: [
          '',
          [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)],
        ],
        email: [
          '',
          [
            Validators.required,
            Validators.pattern(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/),
          ],
        ],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required],
        pais: ['', [Validators.pattern(/^[a-zA-Z ]+$/)]],
        provincia: ['', [Validators.pattern(/^[a-zA-Z ]+$/)]],
        postal: [''],
        ciudad: ['', [Validators.pattern(/^[a-zA-Z ]+$/)]],
        direccion: [''],
        detalle_dir: [''],
        check_fact: [''],
      },
      {
        validators: [this.camposIguales('password', 'confirmPassword')],
      }
    );
  }

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

  login_create() {
    if (this.registerForm.invalid) return;

    const registerBody: FormRegisterInput = {
      email: this.registerForm.value.email,
      password: this.registerForm.value.password,
      name: this.registerForm.value.name,
      lastName: this.registerForm.value.lastname,
    };

    const addressBody: FormShippingDetail = {
      name: this.registerForm.value.name,
      lastName: this.registerForm.value.lastname,
      country: this.registerForm.value.pais,
      provincia: this.registerForm.value.provincia,
      postalCode: this.registerForm.value.postal,
      city: this.registerForm.value.ciudad,
      address1: this.registerForm.value.direccion,
      address2: this.registerForm.value.detalle_dir,
      primaryAddress: this.registerForm.value.check_fact,
      active: true,
      shippingDetailsName: 'principal',
    };

    this.authService.register(registerBody).subscribe({
      next: (res) => {
        console.log(res);
        this.visible = true;
        this.getUserData(addressBody, res.token);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  getUserData(addressBody: FormShippingDetail, accessToken: string) {
    const { userId }: Payload = jwtDecode(accessToken);

    this.userService
      .addShipingDetails(addressBody, userId)
      .pipe(take(1))
      .subscribe((resp: ShippingDetailResponse) => {
        console.log(resp);
      });
  }

  closeModal() {
    this.visible = false;
  }
}
