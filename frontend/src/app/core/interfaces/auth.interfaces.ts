export interface LoginResponse {
  email: string;
  token: string;
  active: boolean;
}

export interface RegisterResponse {
  email: string;
  password: string;
  verificationCode: string;
  fullName: string;
  token: string;
}

export interface FormLoginInput {
  email: string;
  password: string;
}

export interface FormRegisterInput {
  email: string;
  password: string;
  name: string;
  lastName: string;
}
