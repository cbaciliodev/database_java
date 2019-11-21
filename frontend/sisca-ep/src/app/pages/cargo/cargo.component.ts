import { Component, OnInit } from '@angular/core';
import { Cargo } from 'src/app/models/cargo.model';
import { CargoService } from 'src/app/services/service.index';
import swal from 'sweetalert';
import { Docente } from 'src/app/models/docente.model';


@Component({
  selector: 'app-cargo',
  templateUrl: './cargo.component.html',
  styleUrls: ['./cargo.component.css']
})
export class CargoComponent implements OnInit {

  public cantidad: number;
  public p: number = 1;
  public paginador: any;

  public _listaCargos: Cargo[];

  constructor(public _serviceCargo: CargoService) { }

  ngOnInit() {

    this.findAllCargos();
  }

  /**
   * CARGOS
   */
  findAllCargos() {

    this._listaCargos = [];
    this._serviceCargo.findAllCargos()
      .subscribe(res => {
        this._listaCargos = res;
        console.log(this._listaCargos);
      })
  }

  deleteCargo(id: number) {

    swal(`Una vez eliminado, no podrás recuperar este registro!`, {
      title: `Estas seguro?`,
      icon: `warning`,
      buttons: [`Cancel`, `Ok`],
      dangerMode: true
    })
      .then((willDelete) => {
        if (willDelete) {
          this._serviceCargo.deleteCargoById(id)
            .subscribe(data => {

              if (data.mensaje == "The DELETE statement conflicted with the REFERENCE constraint \"FK__TBL_DOCEN__FK_IC__398D8EEE\". The conflict occurred in database \"REUNION\", table \"dbo.TBL_DOCENTES\", column 'FK_ICOD_CARGO'.") {
                swal(`Lo siento, no de puede borrar`, `El registro esta relacionado con la Tabla docente`, {
                  icon: `error`,
                });
              } else if (data.mensaje == `El registro ha sido eliminado con éxito.`) {
                swal(`Buen Trabajo`, ` ${data.mensaje}`, {
                  icon: `success`,
                });
              }
              this.findAllCargos();
            });

        } else {
          swal(`Informacion`, `El registro no fue eliminado!`, `info`);
        }
      });
  }

  }


