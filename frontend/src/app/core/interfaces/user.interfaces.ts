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
  active: boolean;
  primaryAddress: boolean;
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
