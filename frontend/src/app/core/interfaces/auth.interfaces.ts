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

export interface UserData {
  accountUuid: string;
  email: string;
  password?: string;
  rol: string;
  createdDate: string;
  lastSessionDate: string;
  active: boolean;
  verificationCode: null;
  tokenPassword: null;
  token: string;
  number: string;
  name: string;
  lastName: string;
  address: string;
  country: string;
  phonesList: PhonesList[];
  shippingDetailsList: ShippingDetailsList[];
}

export interface PhonesList {
  phoneUuid: string;
  phoneLabel: string;
  phoneNumber: string;
  cityCode: string;
  countryCode: string;
}

export interface ShippingDetailsList {
  shippingDetailUuid: string;
  shippingDetailsName: string;
  name: string;
  lastName: string;
  address1: string;
  address2: string;
  postalCode: string;
  provincia: string;
  city: string;
  country: string;
  active: boolean;
  primaryAddress: boolean;
  gift: boolean;
}

export interface Payload {
  sub: string;
  roles: string;
  userId: string;
  exp: number;
}
