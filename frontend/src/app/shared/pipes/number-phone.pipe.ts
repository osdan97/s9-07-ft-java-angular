import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'numberPhone'
})
export class NumberPhonePipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
