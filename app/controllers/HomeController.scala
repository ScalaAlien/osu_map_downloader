package controllers

import javax.inject._
import play.api.libs.ws._
import play.api.mvc._

import scala.concurrent.Future


/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
//(ws: WSClient)これを追加したので、テストが動かなくなっているのですが、どんな感じに修正すればいいのでしょうか。
@Singleton
class HomeController @Inject()(cc: ControllerComponents)(ws: WSClient) extends AbstractController(cc) {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index() = Action { implicit request: Request[AnyContent] =>
    //下記にアクセスし、Jsonが返ってくることを確認したいです。
    //https://osu.ppy.sh/api/get_beatmaps?k=95ad3c5766e0a595fe278de23e42e4fd898ff429&
    val url = "https://osu.ppy.sh/api/get_beatmaps"
    val request: WSRequest = ws.url(url)
    val complexRequest: WSRequest =
      request.addQueryStringParameters("k" -> "95ad3c5766e0a595fe278de23e42e4fd898ff429")
    val futureResponse: Future[WSResponse] = complexRequest.get()
    println(futureResponse)
    Ok(views.html.index())
  }
}
