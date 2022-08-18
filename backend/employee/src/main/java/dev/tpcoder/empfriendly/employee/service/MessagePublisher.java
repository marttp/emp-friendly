package dev.tpcoder.empfriendly.employee.service;

public interface MessagePublisher<T> {
  void publish(final T t);
}
