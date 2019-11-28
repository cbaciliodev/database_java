import { Component, OnInit } from '@angular/core';
import { SidebarService } from '../../services/service.index';
declare function init_plugins();

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styles: []
})
export class SidebarComponent implements OnInit {

  constructor(public _sidebarMenu: SidebarService) { }

  ngOnInit() {
    //init_plugins();
  }

}
