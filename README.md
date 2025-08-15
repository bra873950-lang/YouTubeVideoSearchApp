# YouTube Video Search App

تطبيق Android للبحث عن مقاطع فيديو في YouTube وعرض النتائج في قائمة منظمة.

## الميزات الرئيسية
- البحث عن مقاطع فيديو باستخدام API YouTube Data v3
- عرض النتائج في RecyclerView مع الصور المصغرة
- التعامل مع حالات مختلفة (تحميل، نتائج فارغة، أخطاء)
- دعم كامل للغات RTL (العربية) و LTR (الإنجليزية)
- واجهة مستخدم مبنية باستخدام Material Design

## المتطلبات التقنية
- Android Studio Flamingo | 2022.2.1 أو أعلى
- JDK 17+
- Android SDK 33+
- حساب Google Cloud Platform مفعل مع YouTube Data API

## المكتبات المستخدمة
- [Retrofit](https://square.github.io/retrofit/) للاتصال بالـ API
- [Glide](https://github.com/bumptech/glide) لتحميل الصور
- [Material Components](https://material.io/develop/android) لتصميم الواجهة
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) لإدارة البيانات

## خطوات الإعداد

1. استنسخ المستودع:
```bash
git clone https://github.com/yourusername/YouTubeVideoSearchApp.git
