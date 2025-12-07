package com.fsk.rabbitmqtutorial;

public class QueueConstants {

    private QueueConstants() {}

    public static final String EXCHANGE_NAME = "notification_queue";

    public static final String SMS_QUEUE = "sms_queue";
    public static final String EMAIL_QUEUE = "email_queue";
    public static final String SLACK_QUEUE = "slack_queue";

    public static final String SMS_ROUTING_KEY = "sms_routing_key";
    public static final String EMAIL_ROUTING_KEY = "email_routing_key";
    public static final String SLACK_ROUTING_KEY = "slack_routing_key";

}
