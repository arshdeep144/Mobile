package setnyk.michael.myapplication

import java.net.URL

/**
 * Created by michael on 02/11/17.
 */
data class HoldAddress(val address1: String, val address2: String, val locality:String, val adminArea:String, val country:String, val postalCode:String, val phone:String, val url:URL) {
}
