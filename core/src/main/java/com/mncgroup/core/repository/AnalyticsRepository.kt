package com.mncgroup.core.repository

/**
 * Repository for analytics
 */
interface AnalyticsRepository {
    /**
     * an function to log analytics event
     * @param name name log
     * @param eventType event type of event
     * @param args array value of logs
     */
    fun logEvent(name: String, eventType: EventType, vararg args: Pair<String, String>)
}

/**
 * Sealed class of event type
 */
sealed class EventType(val eventName: String)

/**
 * Class to create new given name event type generic
 * @param eventName a name of event
 */
class GenericEvent(eventName: String) : EventType(eventName)

object CrashEvent : EventType("Crash")
object LoggingEvent : EventType("Log")
object PurchaseEvent : EventType("Purchase")
object AddToCartEvent : EventType("AddToCart")
object CheckoutEvent : EventType("Checkout")
object InquiryEvent : EventType("Inquiry")
object PaymentEvent : EventType("Payment")
object LoginEvent : EventType("Login")
object RegistrationEvent : EventType("Registration")
