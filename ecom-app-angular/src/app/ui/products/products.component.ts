import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent {
  public  products : any;
  constructor(private http : HttpClient) {
  }
  ngOnInit() {
    this.http.get("http://localhost:8888/INV-SERVICE/api/products").subscribe({
      next : data => {
        this.products = data;
      },
      error : err => {
        console.log(err);
      }
    })
  }
}
