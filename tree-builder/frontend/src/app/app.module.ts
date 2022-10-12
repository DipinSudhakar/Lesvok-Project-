import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatTreeModule } from '@angular/material/tree';
import { MatIconModule } from '@angular/material/icon';

import { AppRoutingModule } from './app-routing.module';

import { NewNodeComponent } from './new-node/new-node.component';
import { HomeComponent } from './home/home.component';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NodeService } from './node-service';

@NgModule({
  declarations: [AppComponent, NewNodeComponent, HomeComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatTreeModule,
    MatIconModule,
    ReactiveFormsModule,
  ],
  providers: [NodeService, NewNodeComponent],
  bootstrap: [AppComponent],
})
export class AppModule {}
