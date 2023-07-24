export interface FormShippingDetail {
  shippingDetailsName: string;
  name: string;
  lastName: string;
  address1: string;
  address2: string;
  postalCode: string;
  provincia: string;
  city: string;
  country: string;
  active?: boolean;
  primaryAddress?: boolean;
}

export interface ShippingDetailResponse {
  name: string;
  lastName: string;
  shippingDetailsName: string;
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

// Generated by https://quicktype.io

export interface FavoriteResponse {
  id: string;
  customers: Customers;
  product: Product;
}

export interface Customers {
  accountUuid: string;
  email: string;
  password: string;
  rol: string;
  createdDate: string;
  lastSessionDate: string;
  active: boolean;
  verificationCode: null;
  tokenPassword: null;
  token: null;
  number: string;
  name: string;
  lastName: string;
  address: null;
  country: null;
  phonesList: any[];
  shippingDetailsList: any[];
}

export interface Product {
  id: string;
  name: string;
  description: string;
  stock: number;
  image: string;
  price: number;
  weight: number;
  country: string;
  minStock: number;
  state: string;
  category: Category;
}

export interface Category {
  id: string;
  name: string;
  description: string;
  number: string;
  state: boolean;
}
