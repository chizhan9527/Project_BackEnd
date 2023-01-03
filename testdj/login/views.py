from django.shortcuts import render
from django.shortcuts import HttpResponse
import json
# Create your views here.
def index(request):
    return HttpResponse('dasda')
def pose(request):
    request.encoding='utf-8'
    mid=request.GET.getlist('angle')
    p_data = json.load(request.body)
    print(len(mid))
    print(mid[0][6])
    print("daasdasd")
    return HttpResponse('dasda')