import { Component, OnInit } from '@angular/core';
import { Router, ActivationEnd } from '@angular/router';
import { filter, map } from 'rxjs/operators';
import { Title, Meta, MetaDefinition } from '@angular/platform-browser';

@Component({
  selector: 'app-breadcrumbs',
  templateUrl: './breadcrumbs.component.html',
  styles: []
})
export class BreadcrumbsComponent implements OnInit {

  public _titulo: string;
  public _link: string;

  constructor(public _router: Router,
    public titule: Title,
    private meta: Meta) {

    this.getDataRouter().subscribe(data => {

      this._titulo = data.titulo;
      this._link = data.link;
      this.titule.setTitle(this._titulo);

      const metaTag: MetaDefinition = {
        name: 'description',
        content: this._titulo
      }
      this.meta.updateTag(metaTag);
    });
  }

  ngOnInit() {
  }

  getDataRouter() {
    return this._router.events.pipe(

      filter(eventos => eventos instanceof ActivationEnd),
      filter((eventos: ActivationEnd) => eventos.snapshot.firstChild === null),
      map((eventos: ActivationEnd) => eventos.snapshot.data)
    )
  }

}
