<?php
class Publisher implements SplSubject
{
    protected $linkedList = array();

    protected $observers = array();

    protected $name;

    protected $event;

    public function __construct($name) {
        $this->name = $name;
    }

    public function attach(SplObserver $observer) {
        $observerKey = spl_object_hash($observer);
        $this->observers[$observerKey] = $observer;
        $this->linkedList[$observerKey] = $observer->getPriority();
        arsort($this->linkedList);
    }

    public function detach(SplObserver $observer) {
        $observerKey = spl_object_hash($observer);
        unset($this->observers[$observerKey]);
        unset($this->linkedList[$observerKey]);
    }

    public function notify() {
        foreach ($this->linkedList as $key => $value) {
            $this->observers[$key]->update($this);
        }
    }

    public function setEvent($event) {
        $this->event = $event;
        $this->notify();
    }

    public function getEvent() {
        return $this->event;
    }

    public function getSubscribers() {
        return $this->getSubscribers();
    }
}

class Observer implements SplObserver
{
    protected $name;
 
    protected $priority = 0;
 
 
    public function __construct($name, $priority=0)
    {
        $this->name =$name;
        $this->priority =$priority;
    }
 
    public function update(SplSubject $publisher){
 
        print_r($this->name.': '. $publisher->getEvent(). PHP_EOL);
 
    }
 
    public  function getPriority(){
        return $this->priority;
    }
}
?>