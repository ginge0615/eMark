import { Component, OnInit, Output } from '@angular/core';
import { MessageService } from 'src/app/services/message.service'

@Component({
  selector: 'app-message-bar',
  templateUrl: './message-bar.component.html',
  styleUrls: ['./message-bar.component.css']
})
export class MessageBarComponent implements OnInit {
  constructor(public msgService :  MessageService) { }

  ngOnInit() {
  }

}
