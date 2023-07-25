import { Component, OnInit, inject, signal } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Product } from 'src/app/core/interfaces/user.interfaces';
import { DataService } from 'src/app/core/services/data/data.service';
import { UserService } from 'src/app/core/services/user/user.service';

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.scss'],
})
export class MyAccountComponent implements OnInit {
  favorites = signal<Product[]>([]);

  dataSerivice = inject(DataService);
  userService = inject(UserService);
  cookieService = inject(CookieService);
  status_casa = false;

  ngOnInit(): void {
    this.getFavorites();
  }

  changeStatus(newBoolean: boolean) {
    this.status_casa = newBoolean;
  }

  getFavorites() {
    this.userService.favorites$.subscribe((favorites) => {
      this.favorites.set(favorites);
    });
  }
}
