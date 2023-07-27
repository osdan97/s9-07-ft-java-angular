import { Pipe, PipeTransform } from '@angular/core';
import { PhonesList } from 'src/app/core/interfaces/auth.interfaces';

@Pipe({
  name: 'numberPhone',
})
export class NumberPhonePipe implements PipeTransform {
  transform(value: PhonesList[], ...args: unknown[]): string {
    const number = `+${value[0].countryCode}-${value[0].cityCode}-${value[0].phoneNumber}`;
    return number;
  }
}
