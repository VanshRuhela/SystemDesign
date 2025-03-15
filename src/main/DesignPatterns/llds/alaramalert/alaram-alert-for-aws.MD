# Design (LLD) Alarm OR Alert Service for AWS - Machine Coding

### Features Required
- Create and Manage Alarms,
- Users can create alarms with conditions (e.g., CPU > 80%, Memory < 20%). 
- Alarms can be configured to monitor metrics across AWS services. 
- Support recurring or one-time alarms. 
- Multi-Metric Monitoring Monitor multiple metrics for an alarm (e.g., both CPU and Memory). 
- Composite alarms to evaluate combined conditions. 
- Notification Mechanism Notify via email, SMS, or push notifications when an alarm triggers. 
- Integrate with AWS SNS (Simple Notification Service). State Management Maintain states like OK, ALARM, and INSUFFICIENT_DATA for alarms. 
- Custom Actions
  - Allow users to specify actions when alarms trigger (e.g., terminate an instance, scale a service).
- Persistence 
  - Store alarm configurations in a database (e.g., DynamoDB).
- Scalability
Support a high number of alarms with minimal latency.

### Design Patterns
- Observer Pattern
    - Used for the notification mechanism where alarms notify multiple subscribers when triggered.
- Builder Pattern
  - Used for creating complex alarm configurations, ensuring immutability and a readable syntax.
- State Pattern
  - Used for managing alarm states like OK, ALARM, and INSUFFICIENT_DATA.
- Strategy Pattern
    - Used for evaluating alarm conditions with different algorithms. 
- Repository Pattern
  - Used for persisting and retrieving alarm configurations from the database.
- Singleton Pattern
  - Used for managing shared components like NotificationManager and StateManager.
