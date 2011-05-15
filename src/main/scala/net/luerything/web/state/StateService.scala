package net.luerything.web.state

/**
 *
 */

trait StateService {
  def add[T](key: String, value: T): StateService

  def get[T](key: String): Option[T]

  def remove[T](key: String): StateService
}