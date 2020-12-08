/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adem_savaş_hw2;




/**
 *
 * @author adem
 */
public class LinkedList implements HW2Interface {
    private DLNode first,end;
    
    public LinkedList(){
        first=null;/*first is first of linked list node*/
        end=null;/*end is end of linked list node*/
    }
    public void Insert(int newElement, int pos) throws Exception {
        if (pos < 0||(pos>0&&first==null))/*if position not exist*/
            throw LinkedListException();
        DLNode Dummy=first;
        if(first==null&&pos==0){/*if linked list is empty*/
            DLNode NewNode = new  DLNode();
            NewNode.Element=newElement;
            first=NewNode;
            end=NewNode;
        }
        else if (pos == 0){/*if linked list is not empty*/
            DLNode NewNode = new  DLNode();
            NewNode.Element = newElement;
            NewNode.right = first;
            first.left=NewNode;
            first = NewNode;
        }
        else{
            for (int i = 0; i < pos-1; i++)
            {
                Dummy = Dummy.right;
                    if (pos > 0 && Dummy == null)/*if position larger size of 
                                                   Linked list */
                        throw LinkedListException();
            }
            DLNode NewNode = new DLNode();
            if(Dummy.right==null){/*add to the end of the linked list*/
               NewNode.Element = newElement;
               NewNode.left=Dummy;
               Dummy.right=NewNode;
               end=NewNode;
            }
            else{
                NewNode.Element = newElement;
                NewNode.right=Dummy.right;
                Dummy.right.left=NewNode;
                Dummy.right=NewNode;
                NewNode.left=Dummy;
            }    
        }
    }
    public int Delete(int pos) throws Exception {
        int DeletedNodeElement;
        if (pos < 1||first==null)/*if linked list is empty get exception*/
            throw LinkedListException();
        DLNode Dummy; 
        if (pos == 1 && first.right != null){    /*Delete the First Element*/
            DeletedNodeElement = first.Element;
            first=first.right;
            first.left=null;
        }
        else if(pos==1&&first.right==null){
            DeletedNodeElement = first.Element;
            first=null;
            end=null;
            
        }
        else{
            Dummy = first;
            for (int i = 0; i < pos-2; i++)
            {
                Dummy = Dummy.right;
                if (pos > 0 && Dummy.right == null)/*if pos larger the size of
                                                     linked list get exception*/
                   throw LinkedListException();
            }
            if(Dummy.right.right==null){/*deleted end of the linked list*/
                DeletedNodeElement =Dummy.right.Element;
                Dummy.right=null;
                end=Dummy;
            }
            else{
                DeletedNodeElement =Dummy.right.Element;
                Dummy.right = Dummy.right.right;
                Dummy.right.left=Dummy.right.left.left;
            }
        }
    return DeletedNodeElement;/*return deleted element*/
    }
    public void ReverseLink(){
        if(first!=null){/*if linked list is empty do nothing*/
            DLNode previousNode = first.left;
            DLNode currentNode = first;
            DLNode nextNode; 
            while(currentNode!=null){
                nextNode = currentNode.right;
                currentNode.right = previousNode;
                currentNode.left = nextNode;
                previousNode = currentNode;
                currentNode = nextNode;
            /*change to right and left which of nodes*/
            }
        end =first;
        first = previousNode;/*get reverse of linked list*/
        }
    }      
    public void Output(){
        DLNode Dummy=first;
        System.out.print("The Elements in the list are : ");
        while (Dummy != null){
            System.out.print(Dummy.Element + " " );
            Dummy = Dummy.right;
        }
        System.out.println("");
    }
    public void ROutput(){
        DLNode Dummy=end;
        System.out.print("The Reverse Elements in the list are : ");
        while(Dummy!=null){
            System.out.print(Dummy.Element + " " );
            Dummy = Dummy.left;
        } 
        System.out.println(" " );
    }
    public void SquashL(){
        int pos=0;
        int count=1;
        int temp,temp1;/*current element and next element*/
        DLNode NewNode=new DLNode();
        DLNode Dummy=first;
        DLNode Dummy1;
        Dummy1 = Dummy;
        while (Dummy != null){
            if(Dummy.right != null){
                temp=Dummy.Element;
                Dummy = Dummy.right;
                temp1=Dummy.Element;
                    if(temp1==temp){/*next element equals current element
                                      increase count */
                        count++; 
                    }
                if(temp1!=temp){/*next element not equals current element*/
                    try {
                    Insert(temp,pos);/*insert current element to pos*/
                    pos++;
                    Insert(count,pos);/*insert count to pos*/
                    pos++;
                    count=1;
                    } 
                    catch (Exception ex) {
                        System.out.println(ex.toString());
                    }
                }
            } 
            else{
                try {
                    
                    Insert(Dummy.Element,pos);
                    pos++;
                    Insert(count,pos);
                } 
                catch (Exception ex) {
                   System.out.println(ex.toString());
                }
                break;
            }
        }
        while (Dummy1 != null){/*delte to linked list which not insert*/
            try {
                Delete(pos+2) ;
                Dummy1=Dummy1.right;
            } 
            catch (Exception ex) {
                System.out.println(ex.toString());
            }   
        }
    }
    public void OplashL(){
        int pos=0;
        DLNode Dummy1;
        DLNode Dummy=first;
        Dummy1=Dummy;
        while(Dummy!=null){
            for(int i=0;i<Dummy.right.Element;i++){
                try {
                    Insert(Dummy.Element,pos);/*insert the element next element
                                                 times*/
                    pos++;
                }
                catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
            Dummy=Dummy.right.right;
        }
        Dummy=Dummy1;
        while (Dummy != null){/*delete the linked list which not insert*/
            try {
                Delete(pos+1) ;
                Dummy=Dummy.right;
            } 
            catch (Exception ex) {
                System.out.println(ex.toString());
            }   
        }
    }
    @Override
    public String toString(){
        DLNode Dummy=first;
        String strlist = "";
        while(Dummy!=null){/*elements of linked list to strlist*/
            strlist+=  Dummy.Element+" ";
            Dummy=Dummy.right;
        }
        return strlist;/*return all elements as string*/
    }
    public Exception LinkedListException(){
       throw new HW2Exception("Not supported yet.");/*throw HW2Exception and get
                                                  message to not supported yet*/
    }
}

