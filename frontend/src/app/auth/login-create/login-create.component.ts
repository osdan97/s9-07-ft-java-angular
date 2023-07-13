import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { FormRegisterInput } from 'src/app/core/interfaces/auth.interfaces';
import { AuthService } from 'src/app/core/services/auth/auth.service';

@Component({
  selector: 'app-login-create',
  templateUrl: './login-create.component.html',
  styleUrls: ['./login-create.component.scss'],
})
export class LoginCreateComponent implements OnInit, OnDestroy {
  visible = true;

  registerForm!: FormGroup;

  formBuilder = inject(FormBuilder);
  authService = inject(AuthService);

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
    return this.formBuilder.group({
      name: ['', [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)]],
      lastname: ['', [Validators.required, Validators.pattern(/^[a-zA-Z ]+$/)]],
      email: [
        '',
        [
          Validators.required,
          Validators.pattern(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/),
        ],
      ],
      password: ['', Validators.required],
      pais: ['', [Validators.pattern(/^[a-zA-Z ]+$/)]],
      provincia: ['', [Validators.pattern(/^[a-zA-Z ]+$/)]],
      postal: [''],
      ciudad: ['', [Validators.pattern(/^[a-zA-Z ]+$/)]],
      direccion: [''],
      detalle_dir: [''],
      check_fact: [''],
    });
  }

  login_create() {
    if (this.registerForm.invalid) return;

    const body: FormRegisterInput = {
      email: this.registerForm.value.email,
      password: this.registerForm.value.password,
      name: this.registerForm.value.name,
      lastName: this.registerForm.value.lastname,
    };

    this.authService.register(body).subscribe({
      next: (res) => {
        console.log(res);
        this.visible = true;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
