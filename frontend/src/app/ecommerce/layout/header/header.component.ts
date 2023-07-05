import { Component, HostListener, signal } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent {
  value: string | undefined;
  headerFixed = signal<boolean>(false);

  @HostListener('window:scroll', ['$event']) activeFixedHeader() {
    if (window.scrollY >= 119) {
      this.headerFixed.set(true);
      console.log(this.headerFixed());
    } else {
      this.headerFixed.set(false);
      console.log(this.headerFixed());
    }
  }
}
