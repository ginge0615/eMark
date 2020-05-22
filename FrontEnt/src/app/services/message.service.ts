import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private isShowMsgFlg: boolean;
  private msgType: string;
  private msg: string;

  constructor() { }

  public isShowMessage() : boolean {
    return this.isShowMsgFlg;
  }

  public hideMessage() {
    this.isShowMsgFlg = false;
  }

  public getMessageType() : string {
    return this.msgType;
  }

  public getMessage() : string {
    return this.msg;
  }

  private _showMsg(msgType : string, msg : string) {
    this.isShowMsgFlg = true;
    this.msgType = msgType;
    this.msg = msg;
  }

  public showErrorMsg(msg: string) {
    this._showMsg("error", msg);
  }

  public showSuccessMsg(msg: string) {
    this._showMsg("success", msg);
  }

  public showWarningMsg(msg: string) {
    this._showMsg("warning", msg);
  }

}
